import * as jwt from 'jwt-decode';
export interface UserJwtPayload extends jwt.JwtPayload {
  firstName?: string;
  lastName?: string;
}
