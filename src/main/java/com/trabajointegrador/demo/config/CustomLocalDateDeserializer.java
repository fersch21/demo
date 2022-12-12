package com.trabajointegrador.demo.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.trabajointegrador.demo.exception.BadRequestException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateDeserializer extends JsonDeserializer<Object> {

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = jsonParser.getText();
        try{
            LocalDate localDate = LocalDate.parse(date, formatter);
            return localDate;
        }catch(Exception ex){
            throw new BadRequestException("el formato de fecha no es aceptado (ej: 22-02-2022)");
        }
    }

}

