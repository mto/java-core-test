import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * A subclass of BufferedReader which skip the comment block
 *
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 6/27/11
 */
public class SkipCommentReader extends BufferedReader
{

   private BufferedReader bufferedReader;

   private final StringBuilder pushbackCache;

   private final static int EOF = -1;

   private State cursorState;

   public SkipCommentReader(Reader reader)
   {
      super(reader);
      pushbackCache = new StringBuilder();
      cursorState = State.ENCOUNTING_ORDINARY_CHARACTER;
   }

   /**
    * Recursive method that read a single character from underlying reader. Encountered comment block
    * is escaped automatically.
    *
    * @return
    * @throws IOException
    */
   public int readSingleCharacter() throws IOException
   {
      int readingChar = readLikePushbackReader();
      if(readingChar == EOF)
      {
         return EOF;
      }
      switch (readingChar)
      {
         case '/':
            int nextCharToRead = read();
            if (nextCharToRead == '*')
            {
               this.cursorState = SkipCommentReader.State.ENCOUNTING_COMMENT_BLOCK_OPENING_TAG;
               advanceToEscapeCommentBlock();
               return readSingleCharacter();
            }
            else
            {
               this.cursorState = SkipCommentReader.State.ENCOUNTING_FORWARD_SLASH;
               pushbackCache.append((char)nextCharToRead);
               return '/';
            }

         case '*':
            if (this.cursorState == SkipCommentReader.State.ENCOUNTING_FORWARD_SLASH)
            {
               this.cursorState = SkipCommentReader.State.ENCOUNTING_COMMENT_BLOCK_OPENING_TAG;
               advanceToEscapeCommentBlock();
               return readSingleCharacter();
            }
            else
            {
               this.cursorState = SkipCommentReader.State.ENCOUNTING_ASTERIK;
               return '*';
            }

         default:
            this.cursorState = SkipCommentReader.State.ENCOUNTING_ORDINARY_CHARACTER;
            return readingChar;
      }

   }

   /**
    * Read from the pushback cache first, then underlying reader
    */
   private int readLikePushbackReader() throws IOException
   {
      if(pushbackCache.length() > 0)
      {
         int readingChar = pushbackCache.charAt(0);
         pushbackCache.deleteCharAt(0);
         return readingChar;
      }
      return read();
   }

   /**
    * Advance in comment block until we reach a comment block closing tag
    */
   private void advanceToEscapeCommentBlock() throws IOException
   {
      if(cursorState != SkipCommentReader.State.ENCOUNTING_COMMENT_BLOCK_OPENING_TAG)
      {
         throw new IllegalStateException("This method should be invoked only if we are entering a comment block");
      }

      int readingChar = read();

      whileLoop:
      while(readingChar != EOF)
      {
         if(readingChar == '/')
         {
            if(this.cursorState == SkipCommentReader.State.ENCOUNTING_ASTERIK)
            {
               break whileLoop; //We 've just escape the comment block
            }
            else
            {
               this.cursorState = SkipCommentReader.State.ENCOUNTING_FORWARD_SLASH;
            }
         }
         else
         {
            this.cursorState = (readingChar == '*')? SkipCommentReader.State.ENCOUNTING_ASTERIK : SkipCommentReader.State.ENCOUNTING_ORDINARY_CHARACTER;
         }

         readingChar = read();
      }
   }

   @Override
   public String readLine() throws IOException
   {
      StringBuilder builder = new StringBuilder();
      int nextChar = readSingleCharacter();
      while(nextChar != EOF)
      {
         if(nextChar == '\n' || nextChar == '\r')
         {
            break;
         }
         builder.append((char)nextChar);
         nextChar = readSingleCharacter();
      }

      String line = builder.toString().trim();

      //Below recursive call is necessary to skip blank between two consecutive comment blocks
      if(line.length() > 0)
      {
         return line;
      }
      else
      {
         return readLine();
      }
   }

   public State getCursorState()
   {
      return this.cursorState;
   }

   public enum State
   {
      ENCOUNTING_FORWARD_SLASH,

      ENCOUNTING_ASTERIK,

      ENCOUNTING_COMMENT_BLOCK_OPENING_TAG,

      ENCOUNTING_COMMENT_BLOCK_CLOSING_TAG,

      ENCOUNTING_ORDINARY_CHARACTER
   }
}
