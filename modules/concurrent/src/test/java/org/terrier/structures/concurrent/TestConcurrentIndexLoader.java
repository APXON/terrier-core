package org.terrier.structures.concurrent;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.terrier.indexing.IndexTestUtils;
import org.terrier.querying.IndexRef;
import org.terrier.structures.ConcurrentIndexLoader;
import org.terrier.structures.Index;
import org.terrier.structures.IndexFactory;
import org.terrier.tests.ApplicationSetupBasedTest;

public class TestConcurrentIndexLoader extends ApplicationSetupBasedTest {

	@Test public void testNewIndex() throws Exception
	{
		Index index = IndexTestUtils.makeIndex(new String[]{"doc1", "doc2"}, new String[]{"the quick fox", "and all that stuff"});
		IndexRef ref = index.getIndexRef();
		assertTrue(IndexFactory.isLoaded(ref));
		System.out.println(ref.toString());
		IndexRef concRef = ConcurrentIndexLoader.makeConcurrent(ref);
		System.out.println(concRef.toString());
		Index concurrent = IndexFactory.of(concRef);
		assertNotNull(concurrent);
		assertTrue(concurrent.getLexicon() instanceof ConcurrentLexicon);
	}
	
	@Test public void testDirectIndex() throws Exception
	{
		Index index = IndexTestUtils.makeIndex(new String[]{"doc1", "doc2"}, new String[]{"the quick fox", "and all that stuff"});
		IndexRef ref = index.getIndexRef();
		assertTrue(IndexFactory.isLoaded(ref));
		System.out.println(ref.toString());
		IndexRef concRef = ConcurrentIndexLoader.makeConcurrent(IndexRef.of(ref.toString()));
		Index concurrent = IndexFactory.of(concRef);
		assertNotNull(concurrent);
		assertTrue(concurrent.getLexicon() instanceof ConcurrentLexicon);
	}
	
}