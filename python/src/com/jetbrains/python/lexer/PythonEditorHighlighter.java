package com.jetbrains.python.lexer;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.ex.util.LexerEditorHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.python.PythonLanguage;
import com.jetbrains.python.highlighting.PySyntaxHighlighterFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * User : catherine
 */
public class PythonEditorHighlighter extends LexerEditorHighlighter {

  public PythonEditorHighlighter(@NotNull EditorColorsScheme scheme, @Nullable Project project, @Nullable VirtualFile file) {
    super(PySyntaxHighlighterFactory.getSyntaxHighlighter(PythonLanguage.getInstance(), project, file), scheme);
  }

  public void documentChanged(DocumentEvent e) {
    synchronized (this) {
      final Document document = e.getDocument();
      Lexer l = getLexer();
      if (l instanceof PythonHighlightingLexer &&
          (((PythonHighlightingLexer)l).getImportOffset() > e.getOffset()
           || ((PythonHighlightingLexer)l).getImportOffset() == -1)) {

        ((PythonHighlightingLexer)l).clearState(e.getDocument().getTextLength());
        setText(document.getCharsSequence());
      }
      else super.documentChanged(e);
    }
  }
}
