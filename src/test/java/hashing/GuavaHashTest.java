package hashing;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.base.Stopwatch;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class GuavaHashTest extends Assert {

	final String BASE_TEST = "This is a very short test string to hash with...";
	
	final int numIterations = 10000; 
	
	@Test 
	public void hashTest() {
		timedHashTest("sha1", Hashing.sha1()); 
		timedHashTest("sha256", Hashing.sha256()); 
		timedHashTest("mm3_128", Hashing.murmur3_128()); 
		timedHashTest("mm3_32", Hashing.murmur3_32()); 
		timedHashTest("md5", Hashing.md5()); 
		timedHashTest("goodFastHash", Hashing.goodFastHash(32)); 
	}
	
	private long timedHashTest(String hashName, HashFunction hf) {
		Stopwatch watch = Stopwatch.createStarted();

		for (int i=0; i < numIterations; i++) {
			// Let's hash a slightly different string each time
			hf.hashString(BASE_TEST + " " + i, Charsets.UTF_8); 
		}

		watch.stop(); 
		System.out.println(hashName + " time: " + watch);
		return watch.elapsed(TimeUnit.MILLISECONDS); 
	}
	
}
