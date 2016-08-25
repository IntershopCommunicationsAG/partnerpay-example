package com.intershop.gradle.tests

import spock.lang.Stepwise

import com.intershop.assembly.test.BaseAssemblySpec

// Use @Stepwise to ensure that feature methods run in the declared order. This
// is most important for the deployment at the beginning and
// for running the DBInit before running the application server
@Stepwise
class AssemblySpec extends BaseAssemblySpec
{
    @Override
    protected Collection<String> getCartridges()
    {
        return ['core']
    }

    @Override
    protected Collection<String> getCartridgesDbInit()
    {
        return ['core']
    }
}
