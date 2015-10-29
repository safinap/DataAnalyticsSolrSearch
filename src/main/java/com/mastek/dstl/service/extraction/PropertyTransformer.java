package com.mastek.dstl.service.extraction;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.Transformer;

import com.mastek.dstl.dvo.EntityType;

public class PropertyTransformer implements Transformer {

	private final String propertyName;
	private final EntityType entityType;

	public PropertyTransformer(String propertyName, EntityType entityType) {
		this.propertyName = propertyName;
		this.entityType = entityType;
	}

	@Override
	public Object transform(Object bean) {
		try {
			if (PropertyUtils.getProperty(bean, propertyName).toString().toUpperCase().equals(entityType.name()))
				return PropertyUtils.getProperty(bean, "text");
			else
				return null;
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("Getter for " + propertyName + " is not visible.", e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Getter for property " + propertyName + " threw an exception.", e.getCause());
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException("No getter defined for property " + propertyName + ".", e);
		}
	}
}
