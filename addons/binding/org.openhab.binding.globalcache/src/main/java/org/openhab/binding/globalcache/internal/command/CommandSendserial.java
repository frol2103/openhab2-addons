/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.globalcache.internal.command;

import java.util.concurrent.LinkedBlockingQueue;

import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.types.Command;
import org.openhab.binding.globalcache.GlobalCacheBindingConstants.CommandType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * The {@link CommandSendserial} class sends a serial command string to the device.
 *
 * @author Mark Hilbush - Initial contribution
 */
public class CommandSendserial extends AbstractCommand {
    private final Logger logger = LoggerFactory.getLogger(CommandSendserial.class);

    private Command command;

    public CommandSendserial(Thing thing, Command command, LinkedBlockingQueue<RequestMessage> queue, String mod,
            String con, String code) {
        // FIXME Fix for SERIAL1 and SERIAL2
        super(thing, queue, "sendserial", CommandType.SERIAL1);
        this.command = command;
        this.deviceCommand = code;
    }

    @Override
    public void parseSuccessfulReply() {
        if (deviceReply == null) {
            return;
        }
        // decode response
    }

    @Override
    public void logSuccess() {
        logger.debug("Execute '{}' succeeded for command {} on thing {} at {}", commandName, command.toString(),
                thing.getUID().getId(), ipAddress);
    }

    @Override
    public void logFailure() {
        logger.error("Execute '{}' failed on thing {} at {}: errorCode={}, errorMessage={}", commandName,
                thing.getUID().getId(), ipAddress, errorCode, errorMessage);
    }
}
