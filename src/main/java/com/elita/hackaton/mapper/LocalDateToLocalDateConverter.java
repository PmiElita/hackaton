package com.elita.hackaton.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

/**
 * @author p.dzeviarylin
 */
@Component
public class LocalDateToLocalDateConverter extends BidirectionalConverter<LocalDate, LocalDate> {
	@Override
	public LocalDate convertTo(final LocalDate source, final Type<LocalDate> destinationType) {
		return source;
	}

	@Override
	public LocalDate convertFrom(final LocalDate source, final Type<LocalDate> destinationType) {
		return source;
	}
}
