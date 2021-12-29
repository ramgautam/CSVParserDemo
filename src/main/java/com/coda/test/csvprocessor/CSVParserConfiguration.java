package com.coda.test.csvprocessor;

import com.coda.test.model.OutputType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CSVParserConfiguration {

   public  final static CSVParserConfiguration DEFAULT_CSV_PARSER_CONFIGURATION = new CSVParserConfiguration();

    private OutputType outputType;
    private boolean staticHeaderPosition;
    private boolean writeOutputOnFileSequentially;
    private String outputLocation;

    public CSVParserConfiguration() {
        this.outputType = OutputType.JSON;
        this.staticHeaderPosition = true;
        this.writeOutputOnFileSequentially = true;
        this.outputLocation=".";
    }



}
