package com.hp.saas.smc;

import com.hp.saas.smc.validator.PageValidator;
import com.hp.saas.smc.validator.SMCValidator;
import org.apache.log4j.Logger;

public class Program {
    static Logger logger = Logger.getLogger(Program.class);

    public static void main(String[] args) {
        PageValidator pv = new SMCValidator();

        logger.info("Starting validation");
        pv.runValidation("https://smlon20p.saas.hp.com/sm/index.do");
    }
}