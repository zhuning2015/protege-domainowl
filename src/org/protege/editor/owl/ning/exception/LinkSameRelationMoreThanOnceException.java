package org.protege.editor.owl.ning.exception;

import java.lang.RuntimeException;

public class LinkSameRelationMoreThanOnceException extends RuntimeException
{
    public LinkSameRelationMoreThanOnceException(String msg)
    {
        super(msg);
    }
}
