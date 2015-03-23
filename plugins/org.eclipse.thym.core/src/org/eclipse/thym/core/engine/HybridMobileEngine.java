/*******************************************************************************
 * Copyright (c) 2013, 2015 Red Hat, Inc. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *     Contributors:
 *          Red Hat Inc. - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.thym.core.engine;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
/**
 * A cordova platform engine. 
 * 
 * @author Gorkem Ercan
 *
 */
public class HybridMobileEngine{
    
    private String id;
    private String name;
    private String version;
    private IPath location;
    private HybridMobileLibraryResolver resolver;
    
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public HybridMobileLibraryResolver getResolver() {
    	return resolver;
    }
    public void setResolver(HybridMobileLibraryResolver resolver) {
    	this.resolver = resolver;
    }
    
    /**
     * Checks if the underlying library compatible and 
     * support the platforms of this engine.
     * 
     * @return status of the library
     */
    public IStatus isLibraryConsistent(){
    	return resolver.isLibraryConsistent();
    }
    
    /**
     * Pre-compiles the libraries used by this engine.
     * @param monitor
     * @throws CoreException
     */
    public void preCompile(IProgressMonitor monitor) throws CoreException{
    	if(resolver.needsPreCompilation())
    	{
    		resolver.preCompile(monitor);
    	}
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof HybridMobileEngine) ){
            return false;
        }
        HybridMobileEngine that = (HybridMobileEngine) obj;
        if(this.getId().equals(that.getId()) 
                && this.getVersion().equals(that.getVersion())){
            return true;
        }
        return super.equals(obj);
    }
    
    @Override
    public int hashCode() {
        if(this.getId() != null && this.getVersion() != null ){
            return this.getId().hashCode()+this.getVersion().hashCode();
        }
        return super.hashCode();
    }
	public IPath getLocation() {
		return location;
	}
	public void setLocation(IPath location) {
		this.location = location;
	}
    
}