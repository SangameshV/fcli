/*******************************************************************************
 * (c) Copyright 2021 Micro Focus or one of its affiliates
 *
 * Permission is hereby granted, free of charge, to any person obtaining a 
 * copy of this software and associated documentation files (the 
 * "Software"), to deal in the Software without restriction, including without 
 * limitation the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit persons to 
 * whom the Software is furnished to do so, subject to the following 
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be included 
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY 
 * KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
 * IN THE SOFTWARE.
 ******************************************************************************/
package com.fortify.cli.ssc.job.cli.cmd;

import java.util.Date;

import com.fortify.cli.common.output.cli.mixin.filter.AddAsDefaultColumn;
import com.fortify.cli.common.output.cli.mixin.filter.OutputFilter;
import com.fortify.cli.ssc.rest.SSCUrls;
import com.fortify.cli.ssc.rest.cli.cmd.AbstractSSCTableOutputCommand;
import com.fortify.cli.ssc.rest.cli.mixin.filter.SSCFilterQParam;

import io.micronaut.core.annotation.ReflectiveAccess;
import kong.unirest.GetRequest;
import kong.unirest.UnirestInstance;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@ReflectiveAccess
@Command(name = "list")
public class SSCJobListCommand extends AbstractSSCTableOutputCommand {
    @Option(names={"--name"}) @OutputFilter @AddAsDefaultColumn
    private String jobName;
    
    @Option(names={"--group"}) @OutputFilter @AddAsDefaultColumn
    private String jobGroup;
    
    @Option(names={"--class"}) @SSCFilterQParam @AddAsDefaultColumn
    private String jobClass;
    
    @Option(names={"--state"}) @SSCFilterQParam @AddAsDefaultColumn
    private String state;
    
    @Option(names={"--cancellable"}) @OutputFilter @AddAsDefaultColumn
    private Boolean cancellable;
    
    @Option(names={"--priority"}) @SSCFilterQParam @AddAsDefaultColumn
    private Integer priority;
    
    @Option(names={"--create-time"}) @OutputFilter @AddAsDefaultColumn
    private Date createTime;
    
    @Option(names={"--start-time"}) @OutputFilter @AddAsDefaultColumn
    private Date startTime;
    
    @Option(names={"--finish-time"}) @OutputFilter @AddAsDefaultColumn
    private Date finishTime;
    
    protected GetRequest generateRequest(UnirestInstance unirest) {
        return unirest.get(SSCUrls.JOBS).queryString("limit","-1");
    }
}
