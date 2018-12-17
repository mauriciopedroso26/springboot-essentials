package br.com.devdojo.demo.error;

public class ValidationErrorDetails extends ErrorDetail {

    private String field;
    private String fliedMessage;

    public String getField() {
        return field;
    }

    public String getFliedMessage() {
        return fliedMessage;
    }

    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private long timestamp;
        private String developerMessage;
        private String field;
        private String fliedMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public Builder field(String field) {
            this.field = field;
            return this;
        }

        public Builder fieldMessage(String fliedMessage) {
            this.fliedMessage = fliedMessage;
            return this;
        }

        public ValidationErrorDetails build() {
            ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
            validationErrorDetails.setDeveloperMessage(this.developerMessage);
            validationErrorDetails.setTitle(this.title);
            validationErrorDetails.setDetail(this.detail);
            validationErrorDetails.setTimestamp(this.timestamp);
            validationErrorDetails.setStatus(this.status);
            validationErrorDetails.field = this.field;
            validationErrorDetails.fliedMessage = this.fliedMessage;

            return validationErrorDetails;
        }


    }
}
