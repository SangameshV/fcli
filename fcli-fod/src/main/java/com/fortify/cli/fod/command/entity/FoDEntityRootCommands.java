/*******************************************************************************
 * (c) Copyright 2020 Micro Focus or one of its affiliates
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
package com.fortify.cli.fod.command.entity;

import com.fortify.cli.common.command.entity.RootCreateCommand;
import com.fortify.cli.common.command.entity.RootDeleteCommand;
import com.fortify.cli.common.command.entity.RootDownloadCommand;
import com.fortify.cli.common.command.entity.RootGetCommand;
import com.fortify.cli.common.command.entity.RootUpdateCommand;
import com.fortify.cli.common.command.entity.RootUploadCommand;
import com.fortify.cli.common.command.util.annotation.RequiresProduct;
import com.fortify.cli.common.command.util.annotation.SubcommandOf;
import com.fortify.cli.common.config.product.Product;
import com.fortify.cli.common.config.product.Product.ProductIdentifiers;

import io.micronaut.core.annotation.ReflectiveAccess;
import picocli.CommandLine.Command;

public class FoDEntityRootCommands {
	@ReflectiveAccess
	@SubcommandOf(RootGetCommand.class)
	@Command(name = ProductIdentifiers.FOD, description = "Get entity data from FoD")
	@RequiresProduct(Product.FOD)
	public static final class FoDGetCommand {}
	
	@ReflectiveAccess
	@SubcommandOf(RootCreateCommand.class)
	@Command(name = ProductIdentifiers.FOD, description = "Create entities in FoD")
	@RequiresProduct(Product.FOD)
	public static final class FoDCreateCommand {}
	
	@ReflectiveAccess
	@SubcommandOf(RootUpdateCommand.class)
	@Command(name = ProductIdentifiers.FOD, description = "Update entities in FoD")
	@RequiresProduct(Product.FOD)
	public static final class FoDUpdateCommand {}
	
	@ReflectiveAccess
	@SubcommandOf(RootDeleteCommand.class)
	@Command(name = ProductIdentifiers.FOD, description = "Delete entities from FoD")
	@RequiresProduct(Product.FOD)
	public static final class FoDDeleteCommand {}
	
	@ReflectiveAccess
	@SubcommandOf(RootUploadCommand.class)
	@Command(name = ProductIdentifiers.FOD, description = "Upload data to FoD")
	@RequiresProduct(Product.FOD)
	public static final class FoDUploadCommand {}
	
	@ReflectiveAccess
	@SubcommandOf(RootDownloadCommand.class)
	@Command(name = ProductIdentifiers.FOD, description = "Download data from FoD")
	@RequiresProduct(Product.FOD)
	public static final class FoDDownloadCommand {}
}
