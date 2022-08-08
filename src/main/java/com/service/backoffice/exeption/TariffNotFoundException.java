package com.service.backoffice.exeption;

public class TariffNotFoundException extends  RuntimeException{

    public TariffNotFoundException(String massage) {
        super(massage);
    }
}
