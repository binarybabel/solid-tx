/*
 * This file is part of the Solid TX project.
 *
 * Copyright (c) 2015. sha1(OWNER) = df334a7237f10846a0ca302bd323e35ee1463931
 * --> See LICENSE.txt for more information.
 *
 * @author BinaryBabel OSS (http://code.binbab.org)
 */

package org.binbab.solidtx.txo;

public class TxBaseObject implements TxObject {
    protected Comparable id;

    @Override
    public Comparable getId() {
        return id;
    }

    @Override
    public void setId(Comparable id) {
        this.id = id;
    }
}