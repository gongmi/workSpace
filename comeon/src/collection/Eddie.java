package collection;

public class Eddie {
	int i;
	String s;
public Eddie() {
	// TODO 自动生成的构造函数存根
}
public Eddie(int i, String s) {
	this.i = i;
	this.s = s;
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
	Eddie other = (Eddie) obj;
	if (i != other.i)
		return false;
	if (s == null) {
		if (other.s != null)
			return false;
	} else if (!s.equals(other.s))
		return false;
	return true;
}
public String getS() {
	return s;
}
public void setS(String s) {
	this.s = s;
}
@Override
public String toString() {
	return "Eddie [i=" + i + ", s=" + s + "]";
}
public int getI() {
	return i;
}
public void setI(int i) {
	this.i = i;
}

}
