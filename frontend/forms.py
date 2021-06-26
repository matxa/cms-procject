"""All the forms"""
from flask_wtf import FlaskForm
from wtforms import StringField, TextAreaField
from wtforms.validators import DataRequired


class LoginForm(FlaskForm):
    """Login Form"""
    email = StringField('email', validators=[DataRequired()])

    def get_email(self):
        """Return form's email"""
        return self.email.data


class RegisterForm(FlaskForm):
    """Register Form"""
    first_name = StringField('first name', validators=[DataRequired()])
    last_name = StringField('last name', validators=[DataRequired()])
    email = StringField('email', validators=[DataRequired()])

    def get_object(self):
        """Return object representation
        of Register class
        """
        return {
            "firstName": self.first_name.data,
            "lastName": self.last_name.data,
            "email": self.email.data
        }


class ArticleForm(FlaskForm):
    """Article Form"""
    title = StringField('title', validators=[DataRequired()])
    content = TextAreaField('content', validators=[DataRequired()])
    email = ""

    def get_object(self):
        """Return object representation
        of Article class
        """
        return {
            "title": self.title.data,
            "content": self.content.data,
            "ownerEmail": self.email
        }
