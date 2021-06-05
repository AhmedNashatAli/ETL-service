package com.consume.rest.app.service;

import com.consume.rest.app.model.input.RowData;
import com.consume.rest.app.model.output.Result;

public interface ETLService {

    Result processRowData(RowData rowData);

    Result processRowData2(RowData rowData);

}
