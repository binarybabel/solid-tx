/*
 * This file is part of the Solid TX project.
 *
 * Copyright (c) 2015. sha1(OWNER) = df334a7237f10846a0ca302bd323e35ee1463931
 * --> See LICENSE.txt for more information.
 *
 * @author BinaryBabel OSS (http://code.binbab.org)
 */

package org.binarybabel.solidtx.core.object;

import org.binarybabel.solidtx.TxStack;

public interface DataMapper {

    public boolean isClassSupported(Class objectClass);

    public Object newObject(Class objectClass) throws DataMapperException;

    public Object loadObject(TxStack stack, ObjectState objectState) throws DataMapperException;

    public void reloadObject(TxStack stack, Object object, ObjectState objectState) throws DataMapperException;

    public ObjectState dumpObjectState(Object object) throws DataMapperException;

}
