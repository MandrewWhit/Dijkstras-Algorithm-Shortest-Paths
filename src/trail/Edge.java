package trail;

public class Edge {
	RestStop stop1;
	RestStop stop2;
	int length;
	
	public Edge(RestStop stop1, RestStop stop2, int length) {
		this.stop1 = stop1;
		this.stop2 = stop2;
		this.length = length;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public RestStop getStop1() {
		return this.stop1;
	}
	
	public RestStop getStop2() {
		return this.stop2;
	}
}
