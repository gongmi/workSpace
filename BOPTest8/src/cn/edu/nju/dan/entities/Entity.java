package cn.edu.nju.dan.entities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Entity {

	public long entityId;
	public List<Author> authors;
	public long conferenceSeriesId;
	public List<Reference> references;
	public List<Field> fields;
	public long journalId;

	public Entity(JSONObject obj) {

		if (obj.has("Id")) {
			this.entityId = obj.getLong("Id");
		}

		if (obj.has("AA")) {
			this.authors = new ArrayList<>();

			JSONArray authorsArray = obj.getJSONArray("AA");
			for (int i = 0; i < authorsArray.length(); i++) {
				JSONObject authorObj = authorsArray.getJSONObject(i);
				Author author = new Author();
				if (authorObj.has("AuId")) {
					author.authorId = authorObj.getLong("AuId");
				}
				if (authorObj.has("AfId")) {
					author.authorAffiliationId = authorObj.getLong("AfId");
				}
				this.authors.add(author);
			}
		}

		if (obj.has("C")) {
			JSONObject cObj = obj.getJSONObject("C");
			if (cObj.has("CId")) {
				this.conferenceSeriesId = cObj.getLong("CId");
			}
		}

		if (obj.has("RId")) {
			this.references = new ArrayList<>();

			JSONArray refArray = obj.getJSONArray("RId");
			for (int i = 0; i < refArray.length(); i++) {
				long rid = refArray.getLong(i);
				Reference reference = new Reference(rid);

				this.references.add(reference);
			}
		}

		if (obj.has("F")) {
			this.fields = new ArrayList<>();

			JSONArray fieldsArray = obj.getJSONArray("F");
			for (int i = 0; i < fieldsArray.length(); i++) {
				JSONObject fObj = fieldsArray.getJSONObject(i);
				if (fObj.has("FId")) {
					this.fields.add(new Field(fObj.getLong("FId")));
				}

			}
		}

		if (obj.has("J")) {
			JSONObject jObj = obj.getJSONObject("J");
			if (jObj.has("JId")) {
				this.journalId = jObj.getLong("JId");
			}

		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Entity)) {
			return false;
		}
		Entity e = (Entity) obj;
		return this.entityId == e.entityId;
	}

	@Override
	public int hashCode() {
		return new Long(this.entityId).hashCode();
	}

}
