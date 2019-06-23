package com.kaitait.registration.domain

import com.openpojo.reflection.impl.PojoClassFactory
import com.openpojo.validation.Validator
import com.openpojo.validation.ValidatorBuilder
import com.openpojo.validation.rule.impl.*
import com.openpojo.validation.test.impl.GetterTester
import com.openpojo.validation.test.impl.SetterTester
import spock.lang.Specification
import spock.lang.Unroll

class PojoSpec extends Specification {
    static List<Class> classesToTest = [User]
    static Validator validator

    def setupSpec() {
        validator = ValidatorBuilder.create()
                .with(new GetterMustExistRule())
                .with(new SetterMustExistRule())
                .with(new NoFieldShadowingRule())
                .with(new NoNestedClassRule())
                .with(new NoPublicFieldsExceptStaticFinalRule())
                .with(new NoStaticExceptFinalRule())
                .with(new SerializableMustHaveSerialVersionUIDRule())
                .with(new EqualsAndHashCodeMatchRule())
                .with(new SetterTester())
                .with(new GetterTester())
                .build()
    }

    @Unroll
    def "class #clazz.name fulfils the POJO contract"() {
        expect:
        validator.validate(PojoClassFactory.getPojoClass(clazz))

        where:
        clazz << classesToTest
    }
}
