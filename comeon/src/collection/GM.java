package collection;

import java.io.Serializable;

public class GM implements Serializable, Comparable<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8386330180726369062L;
	private int i;
	private String s;

	public GM() {
	}

	public GM(int i, String s) {
		this.i = i;
		this.s = s;
	}

	public String getS() {
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + ((s == null) ? 0 : s.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GM other = (GM) obj;
		if (i != other.i)
			return false;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GM [i=" + i + ", s=" + s + "]  ";
	}

	public int compareTo(Object o) {
		GM gm = (GM) o;
		return Integer.compare(this.i, gm.i);

	}

}
