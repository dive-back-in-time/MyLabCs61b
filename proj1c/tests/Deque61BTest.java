import deque.ArrayDeque61BB;
import deque.Deque61B;
import deque.LinkedListDeque61BB;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class Deque61BTest {

    @Test
    public void testEqualDeques61b(){
        Deque61B<String> lld1 = new ArrayDeque61BB<>();
        Deque61B<String> lld2 = new ArrayDeque61BB<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    public void Deque61BtoStringTest(){
        Deque61B<String> lld1 = new ArrayDeque61BB<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        Deque61B<String> lld2 = new LinkedListDeque61BB<>();
        lld2.addFirst("world");
        lld2.addFirst("new");
        lld2.addFirst("Hello");

        assertThat(lld1.toString()).isEqualTo("[front, middle, back]");
        assertThat(lld2.toString()).isEqualTo("[Hello, new, world]");
    }

}
