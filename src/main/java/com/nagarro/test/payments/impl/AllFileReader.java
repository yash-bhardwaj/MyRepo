package com.nagarro.test.payments.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AllFileReader {
    String fileType;
    String fileLocation;

    public String getFileType() {
        return fileType;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public AllFileReader(final Builder builder) {
        fileType = builder.fileType;
        fileLocation = builder.fileLocation;
    }

    public static class Builder {
        String fileType;
        String fileLocation;

        public String getFileType() {
            return fileType;
        }

        public Builder setFileType(final String fileType) {
            this.fileType = fileType;
            return this;
        }

        public String getFileLocation() {
            return fileLocation;
        }

        public Builder setFileLocation(final String fileLocation) {
            this.fileLocation = fileLocation;
            return this;
        }

        public AllFileReader build() {
            return new AllFileReader(this);
        }
        public Builder(){

        }

        @Override
        public String toString() {
            return "Builder{" + "fileType='" + fileType + '\'' + ", fileLocation='" + fileLocation + '\'' + '}';
        }
    }

    public BufferedReader getFileReader() throws IOException {
        return new BufferedReader(new FileReader(new File(this.getFileLocation())));
    }
}
