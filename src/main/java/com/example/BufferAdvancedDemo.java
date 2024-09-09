package com.example;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

public class BufferAdvancedDemo {

    public static void main(String[] args) {

        // 1. Create a direct ByteBuffer with a capacity of 10 bytes
        ByteBuffer directByteBuffer = ByteBuffer.allocate(10);

        // Write data to the buffer
        fillBuffer(directByteBuffer,1,10);

        // Prepare the buffer for reading with flip()
directByteBuffer.flip();
printBuffer(directByteBuffer,"Initial buffer content:");

        // Clear the buffer and write new data
directByteBuffer.clear();
fillBuffer(directByteBuffer,11,10);

        // Rewind the buffer to re-read the newly written data
directByteBuffer.rewind();
        // Use compact() to retain unread data and allow for writing new data
        directByteBuffer.compact();
printBuffer(directByteBuffer,"Rewinded buffer content");


        System.out.println("After compact position :" + directByteBuffer.position());

        // Mark and reset example
directByteBuffer.flip();
// Mark current position
        directByteBuffer.mark();
        System.out.println("Marked :" + directByteBuffer.get());
        //Reset to marked position
        directByteBuffer.reset();
        System.out.println("After reset :" + directByteBuffer.get());

        // Set byte order and use a view buffer (CharBuffer)
directByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        CharBuffer charBuffer = directByteBuffer.asCharBuffer();
        charBuffer.put('A');
        charBuffer.flip();
        System.out.println("CharBuffer content:" + charBuffer.get());

        // Access the backing array in a heap-based buffer
ByteBuffer heapBuffer = ByteBuffer.allocate(10);
byte[] array = heapBuffer.array();
array[0] = 43;
        System.out.println("Backed array first element !"  + " " + heapBuffer.get(0));
    }

// fill the buffer with data
    private static void fillBuffer(ByteBuffer directByteBuffer, int start, int length) {
        for (int i = 0; i < length; i++) {
            directByteBuffer.put((byte) (start+i));
        }
    }

    //print the buffer's content
private static void printBuffer(ByteBuffer buffer,String message){
    System.out.println(message);
    while (buffer.hasRemaining()){
        System.out.println(buffer.get()+ " ");
    }
    System.out.println("\n---");
}


}
