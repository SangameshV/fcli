/**
 * Copyright 2023 Open Text.
 *
 * The only warranties for products and services of Open Text 
 * and its affiliates and licensors ("Open Text") are as may 
 * be set forth in the express warranty statements accompanying 
 * such products and services. Nothing herein should be construed 
 * as constituting an additional warranty. Open Text shall not be 
 * liable for technical or editorial errors or omissions contained 
 * herein. The information contained herein is subject to change 
 * without notice.
 */
package com.fortify.cli.ftest.tool

import java.nio.file.Files
import java.nio.file.Path

import com.fortify.cli.ftest._common.Fcli
import com.fortify.cli.ftest._common.spec.FcliBaseSpec
import com.fortify.cli.ftest._common.spec.Prefix
import com.fortify.cli.ftest._common.spec.TempDir

import spock.lang.Shared
import spock.lang.Stepwise

@Prefix("tool.sc-client") @Stepwise
class ToolScClientSpec extends FcliBaseSpec {
    @Shared @TempDir("fortify/tools") String baseDir;
    @Shared String version = "23.1.0"
    @Shared Path globalBinScript = Path.of(baseDir).resolve("bin/scancentral.bat")
    @Shared Path binScript = Path.of(baseDir).resolve("sc-client/${version}/bin/scancentral.bat")
    
    def "install"() {
        def args = "tool sc-client install -y -v=${version} -b ${baseDir} --progress none"
        when:
            def result = Fcli.run(args, {it.expectZeroExitCode()})
        then:
            verifyAll(result.stdout) {
                size()>0
                it[0].replace(' ', '').equals("NameVersionAliasesStableInstalldirAction")
                it[1].contains(" INSTALLED")
                Files.exists(binScript);
                Files.exists(globalBinScript);
            }
    }
    
    def "listVersions"() {
        def args = "tool sc-client list"
        when:
            def result = Fcli.run(args)
        then:
            verifyAll(result.stdout) {
                size()>0
                it[0].replace(' ', '').equals("NameVersionAliasesStableInstalldir")
                it[1].replace(" ", "").startsWith("sc-client")
            }
    }
    
    def "uninstall"() {
        def args = "tool sc-client uninstall -y -v=${version} --progress none"
        when:
            def result = Fcli.run(args)
        then:
            verifyAll(result.stdout) {
                size()>0
                it[0].replace(' ', '').equals("NameVersionAliasesStableInstalldirAction")
                it[1].contains(" UNINSTALLED")
                // TODO fcli currently doesn't delete/update global bin script
                Files.exists(globalBinScript);
                !Files.exists(binScript);
            }
    }
    
}
