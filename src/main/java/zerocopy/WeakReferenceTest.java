package zerocopy;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakReferenceTest {

    public static void test() throws InterruptedException {
        ReferenceQueue referenceQueue = new ReferenceQueue();

        byte[] buffer = new byte[10 * 1024 * 1024];

        WeakReference weakReference = new WeakReference(buffer, referenceQueue);

        // 失去强引用
        buffer = null;
        Reference ref = referenceQueue.poll();
        System.out.println(ref == null);
        System.out.println(weakReference.get());

        System.gc();
        Thread.sleep(1000);

        Reference ref1 = referenceQueue.poll();
        System.out.println(ref1 == weakReference);
        System.out.println(weakReference.get());
    }


    public static void main(String[] args) throws InterruptedException {
        test();
    }
}
