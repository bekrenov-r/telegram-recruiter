import * as jwt from 'jwt-decode';
export interface JwtPayload extends jwt.JwtPayload {
  roles: string;
}
