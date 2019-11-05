package de.fhg.iais.roberta.worker;

import de.fhg.iais.roberta.bean.UsedMethodBean;
import de.fhg.iais.roberta.visitor.collect.AbstractLanguageCollectorVisitor;
import de.fhg.iais.roberta.visitor.collect.VorwerkUsedMethodCollectorVisitor;

public class VorwerkUsedMethodCollectorWorker extends AbstractUsedMethodCollectorWorker {
    @Override
    protected AbstractLanguageCollectorVisitor getVisitor(UsedMethodBean.Builder builder) {
        return new VorwerkUsedMethodCollectorVisitor(builder);
    }
}
