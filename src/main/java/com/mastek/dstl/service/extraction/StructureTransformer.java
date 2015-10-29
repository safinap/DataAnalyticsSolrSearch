package com.mastek.dstl.service.extraction;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.mastek.dstl.dvo.AlchemyEntity;
import com.mastek.dstl.dvo.EntityType;

public class StructureTransformer {
	private EntityType[] types;

	public StructureTransformer(EntityType[] types) {
		this.types = types;
	}

	public Map<EntityType, List<String>> flatten(List<AlchemyEntity> entities) {
		Map<EntityType, List<String>> entitySet = new HashMap<>();
		for (EntityType type : types) {
			List<String> list = transform(type, entities);
			list.removeAll(Collections.singleton(null));
			entitySet.put(type, list);
		}
		return entitySet;
	}

	private static List<String> transform(EntityType entityType, List<AlchemyEntity> entities) {
		return (List<String>) CollectionUtils.collect(entities, new PropertyTransformer("type", entityType));
	}

}
