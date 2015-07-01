package org.protege.editor.owl.ning.exception;

import java.lang.RuntimeException;

/**
 * The basic exception for DomainOWL plugin only with exception
 * message
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class BasicException extends RuntimeException
{
    public BasicException(String msg)
    {
        super(msg);
    }
}
