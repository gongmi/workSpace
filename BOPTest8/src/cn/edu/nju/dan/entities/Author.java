package cn.edu.nju.dan.entities;

public class Author {
	public long authorId;
	public long authorAffiliationId;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Author) {
			return ((Author) obj).authorId == this.authorId && ((Author) obj).authorAffiliationId == this.authorAffiliationId;
		}
		return false;
	}

}
