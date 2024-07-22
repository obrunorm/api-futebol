package com.meli.apifutebol.enums;

public enum Estados {
        AC, AL, AP, AM, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PR, PE, PI, RJ, RN, RS, RO, RR, SC, SP, SE, TO;

        public static boolean contains(String test) {
                for (Estados estado : Estados.values()) {
                        if (estado.name().equals(test)) {
                                return true;
                        }
                }
                return false;
        }
}

