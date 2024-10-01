import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

    @Test
    @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    void addFirstAndaddLastTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(5);//[ ,5, , , , , , ]
        deque.addFirst(7);//[7,5, , , , , , ]
        deque.addFirst(8);//[7,5, , , , , ,8]
        deque.addLast(1);//[7,5,1, , , , ,8]
        deque.addLast(6);//[7,5,1,6, , , ,8]
        assertThat(deque.toList()).containsExactly(7, 5, 1, 6, null, null, null, 8).inOrder();
    }

}
