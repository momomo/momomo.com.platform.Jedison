package m.jedison;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter(AccessLevel.PUBLIC)
@Getter
public abstract class JedisonPoolBaseConfig {
    protected int minIdle = 32;
}
