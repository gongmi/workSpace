package cn.edu.nju.dan.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.edu.nju.dan.entities.Author;
import cn.edu.nju.dan.entities.Entity;
import cn.edu.nju.dan.entities.Field;
import cn.edu.nju.dan.entities.Reference;

public class Utils {

	public static HashSet<Long> getRIds(Entity entity) {
		HashSet<Long> rids = new HashSet<>();
		if (entity.references != null) {
			for (Reference reference : entity.references) {
				rids.add(reference.referenceId);
			}
		}
		rids.remove(0L);
		return rids;
	}

	public static HashSet<Long> getFIds(Entity entity) {
		HashSet<Long> fids = new HashSet<>();
		if (entity.fields != null) {
			for (Field field : entity.fields) {
				fids.add(field.fieldOfStudyId);
			}
		}
		fids.remove(0L);
		return fids;
	}

	public static HashSet<Long> getAuIds(Entity e) {
		HashSet<Long> auids = new HashSet<>();
		if (e.authors != null) {
			for (Author author : e.authors) {
				auids.add(author.authorId);
			}
		}
		auids.remove(0L);
		return auids;
	}

	public static HashSet<Long> getIds(List<Entity> entityList) {
		HashSet<Long> ids = new HashSet<>();
		if (entityList != null) {
			for (Entity entity : entityList) {
				ids.add(entity.entityId);
			}
		}
		ids.remove(0L);
		return ids;
	}

	public static HashSet<Long> getAfId(long authorId, List<Entity> entityList) {
		HashSet<Long> afids = new HashSet<>();
		for (Entity entity : entityList) {
			if (entity.authors != null) {
				for (Author author : entity.authors) {
					if (author.authorId == authorId) {
						afids.add(author.authorAffiliationId);
					}
				}
			}
		}
		afids.remove(0L);
		return afids;
	}

	public static List<Entity> getAuthorEntityList(long auid, List<Entity> entityList) {
		List<Entity> results = new ArrayList<>();
		for (Entity entity : entityList) {
			if (entity.authors != null) {
				for (Author author : entity.authors) {
					if (author.authorId == auid) {
						results.add(entity);
						break;
					}
				}
			}
		}
		return results;
	}

}
