package com.doantotnghiep.nvt.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Component
public class Sftp {
    private final Logger log = LogManager.getLogger(Sftp.class);
    
    public ChannelSftp createChannelSftp() {
        try {
            JSch jSch = new JSch();
            String userName = "emailserv";
            String ftpUrl = "172.23.160.1";
            String password = "abc@123";
            Session session = jSch.getSession(userName, ftpUrl, 22);
            session.setConfig("StrictHostKeyChecking", "no");
            try {
                session.setPassword(password);
            } catch (Exception e) {
                log.error("Password is null", e);
            }
            session.connect(15000);
            Channel channel = session.openChannel("sftp");
            channel.connect(15000);
            return (ChannelSftp) channel;
        } catch (JSchException ex) {
            log.error("Create ChannelSftp error", ex);
        }

        return null;
    }

    public void disconnectChannelSftp(ChannelSftp channelSftp) {
        try {
            if (channelSftp == null)
                return;

            if (channelSftp.isConnected())
                channelSftp.disconnect();

            if (channelSftp.getSession() != null)
                channelSftp.getSession().disconnect();

        } catch (Exception ex) {
            log.error("SFTP disconnect error", ex);
        }
    }
}
