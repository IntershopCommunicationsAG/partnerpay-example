package com.intershop.adapter.payment.partnerpay.capi.propgroups.ui;

import com.intershop.ui.web.capi.property.editor.Editor;
import com.intershop.ui.web.capi.property.editor.PropertyType;
import com.intershop.ui.web.capi.property.editor.ui.UIEditor;

/**
 * The editor class for the lucky number - it can convert form value to editor and vice versa.
 */
public class LuckyNumberEditor implements Editor<Integer>
{
    @Override
    public boolean isSupported(PropertyType propertyType)
    {
        return propertyType.getValueType().equals(Integer.class);
    }

    @Override
    public UIEditor getUIEditor(Integer value, PropertyType propertyType, Object owner)
    {
        LuckyNumberUIEditor editor = new LuckyNumberUIEditor();

        if (value != null)
        {
            editor.setLuckyNumber(value);
        }

        return editor;
    }

    @Override
    public Integer getValue(UIEditor editor, PropertyType propertyType, Object owner)
    {
        LuckyNumberUIEditor luckyNumber = (LuckyNumberUIEditor)editor;

        return luckyNumber.getLuckyNumber();
    }
}