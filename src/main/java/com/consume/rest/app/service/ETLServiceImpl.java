package com.consume.rest.app.service;

import com.consume.rest.app.model.input.Attendee;
import com.consume.rest.app.model.input.Partner;
import com.consume.rest.app.model.input.RowData;
import com.consume.rest.app.model.output.Country;
import com.consume.rest.app.model.output.Result;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ETLServiceImpl implements ETLService {

    @Override
    public Result processRowData(final RowData rowData) {
        final Result result = new Result();
        final Iterator<String> countries = getCountries(rowData);
        final List<Attendee> attendees = explodeByAvailableDates(rowData);
        final Map<String, List<Attendee>> attendeesGroupedByCountry = attendees.stream()
                                                                               .collect(Collectors.groupingBy(attendee -> attendee
                                                                                       .getCountry()));

        while (countries.hasNext()) {
            final String country = countries.next();
            if (attendeesGroupedByCountry.get(country) != null) {
                final List<Attendee> attendeesGroupByDateAndCountry = getAttendeesGroupByStartDate(
                        attendeesGroupedByCountry.get(country));
                result.getCountries()
                      .add(mapAttendeesToCountry(attendeesGroupedByCountry.get(country),
                                                 attendeesGroupByDateAndCountry.get(0).getStartDate(),
                                                 country));

            }
            else { result.getCountries().add(new Country(country)); }
        }

        return result;

    }

    private List<Attendee> explodeByAvailableDates(final RowData rowData) {
        final List<Attendee> attendees = new ArrayList<>();
        for (final Partner p : rowData.getPartners()) {
            final Set<LocalDate> set = new HashSet(Arrays.asList(p.getAvailableDates()));
            for (final LocalDate startDate : p.getAvailableDates()) {
                if (set.contains(startDate.plusDays(1))) {
                    attendees.add(new Attendee(p.getEmail(),
                                               p.getCountry(),
                                               startDate,
                                               startDate.plusDays(1)));
                }

            }

        }
        return attendees;
    }

    private List<Attendee> getAttendeesGroupByStartDate(final List<Attendee> attendees) {
        final Map<LocalDate, List<Attendee>> attendeesGroupByStartDate = attendees.stream()
                                                                                  .collect(Collectors.groupingBy(item -> item
                                                                                          .getStartDate()));
        final LocalDate startDate = Collections.max(attendeesGroupByStartDate.entrySet(),
                                                    (entry1, entry2) -> entry1.getValue().size() - entry2.getValue()
                                                                                                         .size())
                                               .getKey();
        return attendeesGroupByStartDate.get(startDate);
    }

    private Iterator<String> getCountries(final RowData rowData) {
        return Arrays.stream(rowData.getPartners())
                     .collect(Collectors.groupingBy(item -> item.getCountry()))
                     .keySet().iterator();
    }

    private Country mapAttendeesToCountry(final List<Attendee> attendees,
                                          LocalDate startDate,
                                          final String country) {
        final Map<LocalDate, List<Attendee>> attendeesGroupByStartDate = attendees.stream()
                                                                                  .collect(
                                                                                          Collectors
                                                                                                  .groupingBy(
                                                                                                          item -> item
                                                                                                                  .getStartDate()));

        final Iterator<LocalDate> iterator = attendeesGroupByStartDate.keySet().iterator();
        while (iterator.hasNext()) {
            final LocalDate l = iterator.next();
            if (l.toDate().before(startDate.toDate()) && attendeesGroupByStartDate.get(l)
                                                                                  .size() == attendeesGroupByStartDate
                    .get(startDate)
                    .size()) {
                startDate = l;
            }
        }
        final List<Attendee> save = attendeesGroupByStartDate.get(startDate);
        final List<String> attendeesEmails = save.stream()
                                                 .map(attendee -> attendee.getEmail())
                                                 .collect(
                                                         Collectors.toList());
        return new Country(attendeesEmails.size(), attendeesEmails, country, startDate);

    }

}
