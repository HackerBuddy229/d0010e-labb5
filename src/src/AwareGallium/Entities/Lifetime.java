package AwareGallium.Entities;

/**
 * @author Rasmus Bengtsson
 * A class representing a period of time with a distinct end
 */
public class Lifetime implements Cloneable{
    public float start;
    public float end = Float.MAX_VALUE;


    public Lifetime(float start) {
        this.start = start;
    }

    public Lifetime(float start, float end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Weather a specific time is within the start end range
     * @param time the time to check alive for
     * @return weather or not the period is still active
     */
    public boolean isAlive(float time) { return time < end && time >= start; }
    public float duration() {
        if (end == Float.MAX_VALUE)
            return 0.0F;

        return end == 0.0F || start == 0.0F ? 0.0F : end-start;
    }

    @Override
    public Lifetime clone() {
        return new Lifetime(this.start, this.end);
    }
}
