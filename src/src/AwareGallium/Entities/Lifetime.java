package AwareGallium.Entities;

public class Lifetime {
    public float start;
    public float end;


    public Lifetime(float start) {
        this.start = start;
    }

    public Lifetime(float start, float end) {
        this.start = start;
        this.end = end;
    }

    public boolean isAlive(float time) { return time < end; }
    public float lifeTime() { return end-start; }
}
