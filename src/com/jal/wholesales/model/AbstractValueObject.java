package com.jal.wholesales.model;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AbstractValueObject implements ValueObject{
	
	public AbstractValueObject () {
		
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
