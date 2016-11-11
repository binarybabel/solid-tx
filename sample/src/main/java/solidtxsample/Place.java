/*
 * This file is part of the Solid TX project.
 *
 * Copyright (c) 2015. sha1(OWNER) = df334a7237f10846a0ca302bd323e35ee1463931
 * --> See LICENSE.txt for more information.
 *
 * @author BinaryBabel OSS (http://code.binbab.org)
 */

package solidtxsample;

import org.binarybabel.solidtx.txo.TxBaseObject;
import org.binarybabel.solidtx.txo.TxField;

public class Place extends TxBaseObject {

    @TxField
    public String name;

}
