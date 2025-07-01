package m.jedison;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter(AccessLevel.PUBLIC)
@Getter
public class JedisonConfigOur extends JedisonConfig<JedisonPoolOurConfig> {
    public JedisonConfigOur() {
        super(new JedisonPoolOurConfig());
    }
    
    @Override
    public JedisonPoolOur newPool(Jedison jedison) {
        return new JedisonPoolOur(this);
    }
}
