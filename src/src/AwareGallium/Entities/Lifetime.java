package AwareGallium.Entities;

import AwareGallium.Infrastructure.Checkout;

public class Lifetime implements Cloneable{
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
    public float duration() { return end-start; }

    @Override
    public Lifetime clone() {
        return new Lifetime(this.start, this.end);
    }
}
