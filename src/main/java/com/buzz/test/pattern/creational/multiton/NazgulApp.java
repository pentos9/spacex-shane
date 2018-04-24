package com.buzz.test.pattern.creational.multiton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NazgulApp {

    private static Logger logger = LoggerFactory.getLogger(NazgulApp.class);

    public static void main(String[] args) {
        //KHAMUL, MURAZOR, DWAR, JI_INDUR, AKHORAHIL, HOARMURATH, ADUNAPHEL, REN, UVATHA;
        logger.info("KHAMUL={}", Nazgul.getInstance(NazgulName.KHAMUL));

        logger.info("MURAZOR={}", Nazgul.getInstance(NazgulName.MURAZOR));
        logger.info("DWAR={}", Nazgul.getInstance(NazgulName.DWAR));
        logger.info("JI_INDUR={}", Nazgul.getInstance(NazgulName.JI_INDUR));
        logger.info("AKHORAHIL={}", Nazgul.getInstance(NazgulName.AKHORAHIL));

        logger.info("KHAMUL={}", NazgulEnum.KHAMUL);
        logger.info("MURAZOR={}", NazgulEnum.MURAZOR);
        logger.info("DWAR={}", NazgulEnum.DWAR);
        logger.info("JI_INDUR={}", NazgulEnum.JI_INDUR);
        logger.info("AKHORAHIL={}", NazgulEnum.AKHORAHIL);


    }
}
