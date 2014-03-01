package com.selenium.test;

import com.selenium.test.validator.PageValidator;
import com.selenium.test.validator.SMCValidator;
import org.apache.log4j.Logger;

public class Program {
    static Logger logger = Logger.getLogger(Program.class);

    public static void main(String[] args) {
        PageValidator pv = new SMCValidator();

        logger.info("Starting validation");
        pv.runValidation("");
    }
}