package com.meli.apifutebol.exceptions;

public class DuplicateNameException extends RuntimeException {

    public DuplicateNameException() {
        super("Já existe um clube com o mesmo nome neste estado.");
    }

}
