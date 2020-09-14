
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PBTTest  {

    boolean fooBar() {
        return true;
    }

    @Property
    boolean concatWithSpaceAddsSpace(@ForAll String l, @ForAll String r) {
        return PBT.concatWithSpace(l, r).equals(l.concat(" ").concat(r));
    }

}