package fr.quithot.com.quithot.sensors;

import fr.quithot.com.quithot.domain.TiltType;

/**
 * Created by telly on 16/03/18.
 */

public interface OrientationConsumer {

    void notifierShake();

    void notifierTilt(TiltType type);
}
