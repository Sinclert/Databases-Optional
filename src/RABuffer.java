package src;

import java.nio.channels.FileChannel;
import fileSystem.utils.Buffer;

/**
 * Buffer Implementation with random page release policy
 */
public class RABuffer extends Buffer{
    
        /**
         * Constructor: 16 pages of 1024 B each
         */
        public RABuffer(){
            super();
        }
        
        /**
         * Constructor: any pages, of given size each
         * 
         * @param numberOfpages Number of pages in the buffer
         * @param blockSize Size (in bytes) of the pages
         */
        public RABuffer(int numberOfpages, int blockSize){
            super(numberOfpages,blockSize);
        }
        
        /**
         * This method returns the page to be released 
		 * (during block loads into buffer, when there is no empty page)
		 * It follows a random policy, but can be altered to implement other policies.
         *@param fc File descriptor (of the block to be loaded).
         *@param blockNumber Identifier of the block (to be loaded into the released page) 
		 *@return The identifier of the page to be released
		 */
        public int releasePagePolicy(FileChannel fc, int blockNumber) {
              int pageout =(int)Math.floor(Math.random()*this.getNumberOfPages());
              System.out.println("\tFull memory. Page "+pageout+" is released to load block "+blockNumber+" (file "+fc+").");
              return pageout;
        }
        
        /**
         * This method is invoked whenever a page is invoked
		 * Allows to keep information on page usage, in order to implement other release policies
		 *@param i Referenced Page.
         */
        public void referencedPage(int i) {
            System.out.println("\tReferenced page "+i);
            // In this version, there is no other policy; but you can change this.
        }
}